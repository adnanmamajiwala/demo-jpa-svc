package com.example.demojpasvc.preset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PRESET")
public class Preset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long practiceId;
    private String typeName;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = PresetJsonConverter.class)
    private List<PresetSetting> settings;

}
