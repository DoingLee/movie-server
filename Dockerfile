FROM hub.c.163.com/public/jdk:1.7.0_03

#目录是相对于"上下文"（DockerFile所在目录）的
COPY target/movie-server-1.0-SNAPSHOT.jar /app/movie-server-1.0-SNAPSHOT.jar

# 启动容器时运行命令
# -Djava.security.egd=file:/dev/./urandom 是为了减少Tomcat的启动速度
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/movie-server-1.0-SNAPSHOT.jar"]


# 启动方式：
#1、每次更新代码记得：mvn clean install -Dmaven.test.skip
#2、镜像构建：docker build -t doing/movie-server-jar .
#3、导出镜像到当前目录：docker save doing/movie-server-jar | gzip > ./movie-server-jar.tar.gz
#4、把镜像拷贝到目标机器
#5、从当前目录下导入镜像：docker load -i ./movie-server-jar.tar.gz
#6、启动时，通过环境变量修改MongoDB host参数：docker run -d --name movie-server -p 8900:8900 -e mongodb_host=192.168.1.106 doing/movie-server-jar


