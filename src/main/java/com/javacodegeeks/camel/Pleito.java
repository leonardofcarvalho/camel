package com.javacodegeeks.camel;

import java.util.Date;

public class Pleito {
    private Integer codigoPleito;
    private Date dataPleito;
    private String situacaoAtivo;

    public Integer getCodigoPleito() {
	return codigoPleito;
    }

    public void setCodigoPleito(Integer codigoPleito) {
	this.codigoPleito = codigoPleito;
    }

    public Date getDataPleito() {
	return dataPleito;
    }

    public void setDataPleito(Date dataPleito) {
	this.dataPleito = dataPleito;
    }

    public String getSituacaoAtivo() {
	return situacaoAtivo;
    }

    public void setSituacaoAtivo(String situacaoAtivo) {
	this.situacaoAtivo = situacaoAtivo;
    }

    @Override
    public String toString() {
	return "codigoPleito:" + codigoPleito + ":" + "dataPleito:" + dataPleito.getHours() + ":" + "situacao:" + situacaoAtivo;
    }
}
