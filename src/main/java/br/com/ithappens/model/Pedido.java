package br.com.ithappens.model;

import br.com.ithappens.model.enums.TipoPedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

  private Long id;
  private Filial filial;
  private TipoPedido tipoPedido;
  private List<ItemPedido> itensPedido;

}