package com.softlab.hospital.core.model;

import lombok.Data;

@Data
public class Info {

    private Long systemId;

    private String province;

    private String city;

    private String hospital;

    private String room;
}