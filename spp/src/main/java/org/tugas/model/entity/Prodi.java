package org.tugas.model.entity;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prodi {
    private String prodi;
    private Double price;

    @Override
    public String toString() {
        return prodi;
    }
}
