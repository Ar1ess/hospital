package com.softlab.hospital.core.model;

import lombok.Data;

@Data
public class Wj {

    private Long systemId;

    private Long docId;

    private String docFile;


    public String getDocFile() {
        return docFile;
    }


    public void setDocFile(String docFile) {
        this.docFile = docFile == null ? null : docFile.trim();
    }
}