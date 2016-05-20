package com.javacodegeeks.camel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RowProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
	Map<String, Object> row = exchange.getIn().getBody(Map.class);
	System.out.println("Processing " + row);
	Pleito pleito = new Pleito();

	pleito.setCodigoPleito(((BigDecimal) row.get("CD_PLEITO")).intValue());
	pleito.setDataPleito((Date) row.get("DT_PLEITO"));
	pleito.setSituacaoAtivo((String) row.get("ST_ATIVO"));

	exchange.getOut().setBody(pleito);
    }

}
