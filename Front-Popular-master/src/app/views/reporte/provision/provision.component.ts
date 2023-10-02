import { AfterViewInit, ViewChild, inject } from '@angular/core';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { UntypedFormGroup } from '@angular/forms';
import * as moment from 'moment';
import { Provision } from './provision.model';
import { catchError } from 'rxjs';
import { ProvisionesService } from 'app/services/reportes/provisiones.service';//loader.service.ts
import { UtilService } from 'app/shared/services/util.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MaterialModule } from 'app/app/mimodule/material.module';
import { MatSnackBar, MatSnackBarRef, MatSnackBarModule, SimpleSnackBar} from '@angular/material/snack-bar';
import { DecimalPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
//import { MatLegacyProgressSpinnerModule as MatProgressSpinnerModule } from "@angular/material/legacy-progress-spinner";

//import { CategoryService } from 'app/shared/services/category.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-provision',
  templateUrl: './provision.component.html',
  styleUrls: ['./provision.component.scss'],
})
export class ProvisionComponent implements OnInit, AfterViewInit {
  
  private provisionesService = inject(ProvisionesService);
  private utilService = inject(UtilService);

  isLoading: Subject<boolean> = this.utilService.isLoading; // Variable para controlar la visibilidad del spinner
  mostrarMensaje: boolean = true; // Variable de control

  decimalPipe = new DecimalPipe('en-US'); // 'en-US' se refiere al idioma y el formato de números que deseas usar

  fechaSeleccionada: Date;

  fecha: Date = new Date(); // Valor por defecto: fecha actual
  provisiones: Provision[] = [];

  totalElements: number = 0;

  reportesProvision: any = {};
  dataSource: MatTableDataSource<any>;

  displayedColumns: string[] = [
    'dvalor_BV',
    'cmoneda',
    'ncuota',
    'fdesembolso',
    'fpago',
    'monto',
    'tea',
    'diastrans',
    'interes',
    'interesprov',
    'igv',
    'total'

  ];

  basicForm: UntypedFormGroup;

  name = 'CAP-ProvisionIC.xlsx';

  private snackBar = inject(MatSnackBar);


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MaterialModule) materialModule: MaterialModule;
  constructor(
    private _snackBar: MatSnackBar,
    private http: HttpClient
  ) { }

  ngOnInit(): void {

  }


  listPageable(p: number, s: number){
   // return this.http.get<any>(`${this.url}/pageable?page=${p}&size=${s}`);
  }

  obtenerReportesProvision(): void {
    const fechaFormateada = this.fecha.toISOString(); // Formatea la fecha en formato ISO 8601
    this.provisionesService
      .getReporteProvision(fechaFormateada) // Envía la fecha formateada como string en el URL
      .pipe(
        catchError((error) => {
          console.log('Error en la solicitud:', error);
          throw error; // Lanza el error para que pueda ser manejado por otros componentes/observadores.
        })
      )
      .subscribe((data) => {
        this.reportesProvision = data; // Asignar los datos obtenidos a la variable this.reportesRiesgo
        this.totalElements = data.length;
        this.dataSource = new MatTableDataSource(this.reportesProvision);
        this.createTable(this.reportesProvision);

      });


  }

  obtenerDatosUsuario() {}

  obtenerReporteProvisiones(){
    var lfecha = this.fechaSeleccionada;
    //  const fechaFormateada = this.formatoFecha(lfecha); // Obtén el string formateado directamente
    const fechaFormateada =moment(lfecha).format("DD-MM-YYYY");
    this.provisionesService.getReporteProvision(fechaFormateada).subscribe(
      (data) => {
        this.reportesProvision = data;
        this.dataSource = new MatTableDataSource(this.reportesProvision);
        this.createTable(this.reportesProvision);
        this.totalElements = data.length;
        this.mostrarMensaje = false;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  private formatoFecha(fecha: Date): string {
    // Formatear fecha en dd-MM-yyyy
    const dia = fecha.getDate().toString().padStart(2, '0');
    const mes = (fecha.getMonth() + 1).toString().padStart(2, '0');
    const anio = fecha.getFullYear();
    return `${dia}-${mes}-${anio}`;

  }

  ngAfterViewInit() {
    // Event listener para 'touchmove' con passive: true
    window.addEventListener('touchmove', this.handleTouchMove, {
      passive: true,
    });
    // Event listener para 'touchstart' con passive: true
    window.addEventListener('touchstart', this.handleTouchStart, {
      passive: true,
    });
  }

  handleTouchMove(event: TouchEvent) {
    // Lógica para manejar el evento 'touchmove'
    // Puedes agregar aquí cualquier código que necesites ejecutar cuando se detecte el evento 'touchmove'
  }

  handleTouchStart(event: TouchEvent) {
  // Lógica para manejar el evento 'touchstart'
  // Puedes agregar aquí cualquier código que necesites ejecutar cuando se detecte el evento 'touchstart'
  }
  /*exportToExcel(): void {
   let data= this.reportesRiesgo;
    console.log(data.length);
    if (data.length>0){
      let workbook = XLSX.utils.book_new();
      let worksheet = XLSX.utils.json_to_sheet(data);

      // first way to add a sheet
      workbook.SheetNames.push('Hoja 1');
      workbook.Sheets['Hoja 1'] = worksheet;

      XLSX.writeFileXLSX(workbook, "AS"+this.name, {});
    }else{
      this._snackBar.open("No hay informacion", 'INFO', {duration: 3000, horizontalPosition: 'right', verticalPosition: 'top'});
    }

  }*/


  exportExcel(){
    var lfecha = this.fechaSeleccionada;
    const fechaFormateada = moment(lfecha).format("DD-MM-YYYY");
    const lfechaExcel = moment(lfecha).format("DDMMMYYYY");
    this.provisionesService.exportCategories(fechaFormateada)
        .subscribe( (data: any) => {
          let file = new Blob([data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
          let fileUrl = URL.createObjectURL(file);
          var anchor = document.createElement("a");
          anchor.download = "CAP-ProvisionIC"+lfechaExcel+".xlsx";
          anchor.href = fileUrl;
          anchor.click();

          this.openSnackBar("Archivo exportado correctamente", "Exitosa");
        }, (error: any) =>{
          this.openSnackBar("No se pudo exportar el archivo", "Error");
        })

  }

  openSnackBar(message: string, action: string) : MatSnackBarRef<SimpleSnackBar>{
    return this.snackBar.open(message, action, {
      duration: 2000
    })

  }

  createTable(data: Provision[]){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    //this.dataSource.sort = this.sort;

        // Aplica el formato solo a las columnas "monto" y "total"
        data.forEach((row) => {
          row.monto = this.decimalPipe.transform(parseFloat(row.monto), '1.2-2');
          row.tea = this.decimalPipe.transform(parseFloat(row.tea), '1.2-2');
          row.interes = this.decimalPipe.transform(parseFloat(row.interes), '1.2-2');
          row.interesprov = this.decimalPipe.transform(parseFloat(row.interesprov), '1.2-2');
          row.igv = this.decimalPipe.transform(parseFloat(row.igv), '1.2-2');
          row.total = this.decimalPipe.transform(parseFloat(row.total), '1.2-2');
        });
    
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
  }
  showMore(e: any){
    this.provisionesService.listPageable(e.pageIndex, e.pageSize).subscribe(data => {

      this.totalElements = data.totalElements;
      this.createTable(data.content);
    });
  }

}
