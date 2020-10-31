package br.com.ithappens.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

  private Filial filial;
  private Produto produto;
  private Long quantidade;

}