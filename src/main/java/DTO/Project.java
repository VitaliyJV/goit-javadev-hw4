package DTO;

import lombok.Data;

@Data
public class Project {
    private long id_project;
    private long client_Id;
    private String start_Date;
    private String finish_Date;
}
