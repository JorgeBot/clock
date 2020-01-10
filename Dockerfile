FROM java:8
ADD clock-0.0.1-SNAPSHOT.jar clock.jar
RUN echo 'Asia/Shanghai' >/etc/timezone
CMD java -jar clock.jar
