//package com.example.demojpasvc.preset;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/presets")
//@CrossOrigin
//public class PresetController {
//
//    private final PresetRepository presetRepository;
//
//    @GetMapping("/{practiceId}/{typeName}")
//    public Preset getByType(@PathVariable("practiceId") Long id,
//                            @PathVariable("typeName") String typeName) {
//        return presetRepository.findByPracticeIdAndTypeName(id, typeName);
//    }
//
//    @PostMapping("/{practiceId}/{typeName}")
//    public Preset save(@RequestBody Preset preset) {
//        return presetRepository.save(preset);
//    }
//}
