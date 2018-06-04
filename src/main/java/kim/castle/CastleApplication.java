package kim.castle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CastleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CastleApplication.class).run(args);
	}
}
