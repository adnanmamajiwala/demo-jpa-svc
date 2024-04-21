//package com.example.demojpasvc.preset;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
//import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
//import static java.util.Objects.isNull;
//
//@Slf4j
//@Converter
//public class PresetJsonConverter implements AttributeConverter<List<PresetSetting>, String> {
//
//    @Override
//    public String convertToDatabaseColumn(List<PresetSetting> presetSetting) {
//        if (isNull(presetSetting)) return null;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.setSerializationInclusion(NON_NULL);
//            return objectMapper.writeValueAsString(presetSetting);
//        } catch (Exception e) {
//            log.error("Json Parsing Utils: Not able to parse the response." + e);
//        }
//        return null;
//    }
//
//    @Override
//    public List<PresetSetting> convertToEntityAttribute(String jsonString) {
//        if (isNull(jsonString)) return null;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            return objectMapper.readValue(jsonString, List.class);
//        } catch (Exception e) {
//            log.error("Json Parsing Utils: Not able to parse the response. {} {}", jsonString, e);
//        }
//        return null;
//    }
//
//}
