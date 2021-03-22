# springboot-protobuf
Write spring-boot microservice with protobuf

The compilation of the protocol buffers specifications will be performed during the maven build.

<plugin>
				<groupId>com.github.os72</groupId>
				<artifactId>protoc-jar-maven-plugin</artifactId>
				<version>3.11.4</version>
				<executions>
					<execution>
						<phase>generate-sources</phase>
						<goals>
							<goal>run</goal>
						</goals>
						<configuration>
							<protocVersion>3.11.4</protocVersion>
							<inputDirectories>
								<include>src/main/protobuf</include>
							</inputDirectories>
						</configuration>
					</execution>
				</executions>
			</plugin>

For protobuf we need to define HttpMessageConverter bean with main class

        @Bean
	public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}
  
**Build**
To build the example just execute:
 mvn clean package


**Run Command**

To run the example just execute:

java -jar target/protobuf-0.0.1-SNAPSHOT.jar
