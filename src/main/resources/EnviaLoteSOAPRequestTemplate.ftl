<soapenv:Envelope
	 xmlns:web="http://www.esocial.gov.br/servicos/empregador/lote/eventos/envio/v1_1_0"
	 xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	 xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	 <soapenv:Header>
		<soapenv:Body>
			<web:EnviarLoteEventos>
				<web:loteEventos><![CDATA[${body}]]></web:loteEventos>
			</web:EnviarLoteEventos>
		</soapenv:Body>
</soapenv:Envelope>
	
