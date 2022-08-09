FROM openjdk:8-jdk
ENV workdir=/projects/eureka-server
RUN mkdir -p ${workdir}
COPY . ${workdir}
WORKDIR ${workdir}
EXPOSE 8761
CMD ["java", "-jar", "eureka-server.jar"]