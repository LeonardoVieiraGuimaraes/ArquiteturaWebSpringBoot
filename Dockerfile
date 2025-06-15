FROM openjdk:24-jdk-slim

# Atualiza os repositórios e instala o Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Comando padrão (ajuste conforme sua aplicação)
CMD ["mvn", "spring-boot:run"]
