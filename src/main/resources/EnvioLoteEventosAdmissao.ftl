<?xml version="1.0" encoding="UTF-8"?>
<esocial:eSocial xmlns:esocial="http://www.esocial.gov.br/schema/lote/eventos/envio/v1_1_0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.esocial.gov.br/schema/lote/eventos/envio/v1_1_0 EnvioLoteEventos-v1_1_0.xsd ">
  <esocial:envioLoteEventos grupo="${headers.grupo}">
    <esocial:ideEmpregador>
      <esocial:tpInsc>1</esocial:tpInsc>
      <esocial:nrInsc>00509018000113</esocial:nrInsc>
    </esocial:ideEmpregador>
    <esocial:ideTransmissor>
      <esocial:tpInsc>2</esocial:tpInsc>
      <esocial:nrInsc>12345678901</esocial:nrInsc>
    </esocial:ideTransmissor>
    <esocial:eventos>
     ${body}
    </esocial:eventos>
  </esocial:envioLoteEventos>
</esocial:eSocial>

