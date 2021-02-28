package br.com.zup.proposta.security;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;

public class CriptografiaConverter implements AttributeConverter<String, String> {

	@Autowired
	Criptografia criptografia;

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return criptografia.encode(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return criptografia.decode(dbData);
	}
}
