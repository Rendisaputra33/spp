package org.tugas.model.entity;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String nama;
    private String nim;
    private String prodi;
    private Integer semester;

    @Override
    public String toString() {
        return nama + "-" + prodi + "-" + semester;
    }
}
