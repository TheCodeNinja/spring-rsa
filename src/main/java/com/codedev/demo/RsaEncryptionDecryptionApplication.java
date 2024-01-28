package com.codedev.demo;

import com.codedev.demo.utils.RSA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RsaEncryptionDecryptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsaEncryptionDecryptionApplication.class, args);

		RSA rsa = new RSA();
		try{
			String encryptedMessage = rsa.encrypt("Hello World");
			log.info("encryptedMessage: {}", encryptedMessage);
			String decryptedMessage = rsa.decrypt(encryptedMessage);
			log.info("decryptedMessage: {}", decryptedMessage);

//			String decrypted = rsa.decrypt(new String("UIFuQr6Ipm++KS19/B5/JJd/j/D0/1JxiArFqS0/b6r/Og+UniojKd0/W7+vj5x6pyFmQYBqaRSrafXoKXNrZnxwX0TK5QpFKykHOUkv8tRgoZD3w0IcBbazvUSR4b3FGxpPTAEPJgl2P5amTucqw5bzOwDUrfMZn9i3rtXyEJY="));
//			log.info("decrypted: {}", decrypted);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
