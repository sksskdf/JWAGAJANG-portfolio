FROM tomcat:8

COPY JWAGAJANG01.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]
