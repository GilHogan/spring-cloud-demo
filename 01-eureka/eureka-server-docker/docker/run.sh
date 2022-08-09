TAG=hogan/eureka-server:1.0
CONTAINER_NAME=eureka-server
docker build -t ${TAG} --rm .
docker rm -f ${CONTAINER_NAME} || true
docker run -d --name ${CONTAINER_NAME} \
  -p 8761:8761 \
  -v /etc/localtime:/etc/localtime:ro \
  -e TZ=Asia/Shanghai \
  -e INSTANCE_NAME=eureka-server \
  ${TAG}