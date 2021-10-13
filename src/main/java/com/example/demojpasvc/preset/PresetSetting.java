package com.example.demojpasvc.preset;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PresetSetting {

    private String name;
    private List<String> facilities = new ArrayList<>();
    private List<String> itemIds = new ArrayList<>();
    private List<String> itemNames = new ArrayList<>();

}
