package com.popularsafi;

import jakarta.persistence.StoredProcedureQuery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class OCP {

    public static void main(String...args){
        int N = 77777777;
        long t;

        {
            StringBuffer sb = new StringBuffer();
            t = System.currentTimeMillis();
            for (int i = N; i --> 0 ;) {
                sb.append("");
            }
            System.out.println("StringBuffer: "+(System.currentTimeMillis() - t));
        }

        {
            StringBuilder sb = new StringBuilder();
            t = System.currentTimeMillis();
            for (int i = N; i > 0 ; i--) {
                sb.append("");
            }
            System.out.println("String: "+(System.currentTimeMillis() - t));
        }
    }
/*
    @Test
    public void testStoredProcedureReturnValue() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-jpa-call-sp-no-out-mysql-example[]
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_phones");
            query.registerStoredProcedureParameter( 1, Long.class, ParameterMode.IN);
            query.setParameter(1, 1L);
            List<Object[]> personComments = query.getResultList();
            //end::sql-jpa-call-sp-no-out-mysql-example[]
            assertEquals(2, personComments.size());
        });
    }*/

    public static void main3(String...arfs) throws ParseException {
        String fecha="2023-07-19T05:00:00.000+00:00";
        // Crear un objeto OffsetDateTime desde la cadena original
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(fecha);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formatear la fecha
        String fechaFormateada = offsetDateTime.format(formatter);

        System.out.println("Fecha formateada: " + fechaFormateada);    }
    public static void main0(String...args){
        Date d=new Date();
        SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(formateo.format(d));
    }
    public static void main1(String...args) throws Exception{
        Date d=new Date();
        ObjetoDTO uno=new ObjetoDTO();
        uno.setFPAGO(d);
        List<ObjetoDTO> dataList = new ArrayList<>();
        dataList.add(uno);
        SimpleDateFormat formateo = new SimpleDateFormat("dd/MM/yyyy");
        BiConsumer<ObjetoDTO, Date> transformation = (dest, v) -> dest.setFPAGO(d);
        List<ObjetoDTO> transformedList = dataList.stream()
                .peek(dto -> transformation.accept(dto, dto.getFPAGO()))
                .collect(Collectors.toList());
        transformedList.forEach(dto -> System.out.println(formateo.format(dto.getFPAGO())));
    }
}
class ObjetoDTO {
    private Date FPAGO;

    public Date getFPAGO() {
        return FPAGO;
    }

    public void setFPAGO(Date FPAGO) {
        this.FPAGO = FPAGO;
    }
}