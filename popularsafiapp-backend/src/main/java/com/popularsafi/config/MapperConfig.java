package com.popularsafi.config;

import com.popularsafi.dto.CalculoDTO;
import com.popularsafi.dto.FondoDTO;
import com.popularsafi.dto.ReporteRiesgoDTO;
import com.popularsafi.model.CalculoIC;
import com.popularsafi.model.Fondo;
import com.popularsafi.model.ReporteRiesgo;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
    public class MapperConfig {
    private static final Logger logger= LoggerFactory.getLogger(MapperConfig.class.getName());
        @Bean("defaultMapper")
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }

        @Bean("fondoMapper")
        public ModelMapper medicMapper() {
            ModelMapper mapper = new ModelMapper();

            //Escritura
            TypeMap<FondoDTO, Fondo> typeMap1 = mapper.createTypeMap(FondoDTO.class, Fondo.class);
            typeMap1.addMapping(FondoDTO::getC_FONDO_ID, (dest, v) -> dest.setC_FONDO_ID((String) v));
            typeMap1.addMapping(FondoDTO::getD_FONDO, (dest, v) -> dest.setD_FONDO((String) v));

            return mapper;
        }

        @Bean("calculoMapper")
        public ModelMapper calculoICMapper() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.info("TimeUnitMillis "+dateFormat.format(System.currentTimeMillis()));
            ModelMapper mapper = new ModelMapper();
            //Escritura
            TypeMap<CalculoDTO, CalculoIC> typeMap1 = mapper.createTypeMap(CalculoDTO.class, CalculoIC.class);
            typeMap1.addMapping(CalculoDTO::getDVALOR_BV, (dest, v) -> dest.setDVALOR_BV((String) v));
            typeMap1.addMapping(CalculoDTO::getNCUOTA, (dest, v) -> dest.setNCUOTA((Integer) v));
            typeMap1.addMapping(CalculoDTO::getFDESEMBOLSO, (dest, v) -> dest.setFDESEMBOLSO((String) v));
            typeMap1.addMapping(CalculoDTO::getMONTO, (dest, v) -> dest.setMONTO((String) v));
            typeMap1.addMapping(CalculoDTO::getFPAGO, (src, v) -> src.setFPAGO((String) v));
            typeMap1.addMapping(CalculoDTO::getTEA, (dest, v) -> dest.setTEA((String) v));
            typeMap1.addMapping(CalculoDTO::getDIASTRANS, (dest, v) -> dest.setDIASTRANS((String) v));
            typeMap1.addMapping(CalculoDTO::getINTERES, (dest, v) -> dest.setINTERES((String) v));
            typeMap1.addMapping(CalculoDTO::getINTERESPROV, (dest, v) -> dest.setINTERESPROV((String) v));
            typeMap1.addMapping(CalculoDTO::getIGV, (dest, v) -> dest.setIGV((String) v));
            typeMap1.addMapping(CalculoDTO::getTOTAL, (dest, v) -> dest.setTOTAL((String) v));
            return mapper;
    }

    @Bean("RiesgoMapper")
    public ModelMapper RiesgoMapper() {
        ModelMapper mapper = new ModelMapper();

        //Escritura
        TypeMap<ReporteRiesgoDTO, ReporteRiesgo> typeMap1 = mapper.createTypeMap(ReporteRiesgoDTO.class, ReporteRiesgo.class);
        typeMap1.addMapping(ReporteRiesgoDTO::getCODIGOTCHN, (dest, v) -> dest.setCODIGOTCHN((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getCMONEDA, (dest, v) -> dest.setCMONEDA((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getNdocumento, (dest, v) -> dest.setNdocumento((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getTapaterno, (dest, v) -> dest.setTapaterno((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getTamaterno, (dest, v) -> dest.setTamaterno((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getTnombres, (dest, v) -> dest.setTnombres((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getDnacimiento, (dest, v) -> dest.setDnacimiento((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getTdireccion, (dest, v) -> dest.setTdireccion((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getCubigeo, (dest, v) -> dest.setCubigeo((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getDepartamento, (dest, v) -> dest.setDepartamento((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getProvincia, (dest, v) -> dest.setProvincia((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getDistrito, (dest, v) -> dest.setDistrito((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getCinmueble, (dest, v) -> dest.setCinmueble((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getActividad, (dest, v) -> dest.setActividad((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getSaldo_actual, (dest, v) -> dest.setSaldo_actual((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getCuotasatrasdas, (dest, v) -> dest.setCuotasatrasdas((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getEstado, (dest, v) -> dest.setEstado((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getCuota, (dest, v) -> dest.setCuota((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getSueldo, (dest, v) -> dest.setSueldo((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getFdesembolso, (dest, v) -> dest.setFdesembolso((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getTipooperacion, (dest, v) -> dest.setTipooperacion((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getS_INFOCORP, (dest, v) -> dest.setS_INFOCORP((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getNVALORIZACION, (dest, v) -> dest.setNVALORIZACION((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getV_EDIFICACION, (dest, v) -> dest.setV_EDIFICACION((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getV_PROPIEDAD, (dest, v) -> dest.setV_PROPIEDAD((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getV_COMERCIAL, (dest, v) -> dest.setV_COMERCIAL((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getV_REALIZACIONSOL, (dest, v) -> dest.setV_REALIZACIONSOL((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getV_REALIZACIONDOL, (dest, v) -> dest.setV_REALIZACIONDOL((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getF_VALORIZACION, (dest, v) -> dest.setF_VALORIZACION((String) v));
        typeMap1.addMapping(ReporteRiesgoDTO::getNcuotas_generadas, (dest, v) -> dest.setNcuotas_generadas((String) v));
        return mapper;
    }
}
