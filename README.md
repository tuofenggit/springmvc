### 部署说明
我自身使用的是STS这个对 SpringBoot 支持良好，但是对Spring MVC（使用自定义tomcat）部署不太友好（或者我的使用方式不对）的IDE 

所以采用了maven 发布到Tomcat的方式

具体如下：

1.在 Tomcat8.5文件在中的tomcat-users.xml 添加

<role rolename="manager" />  
<role rolename="manager-gui"/>  
<role rolename="manager-script"/>  
<role rolename="manager-jmx"/>  
<role rolename="manager-status"/>  
<user password="tomcat" username="tomcat" roles="manager-gui,manager-script,manager,manager-jmx,manager-status" />

2.在 maven setting.xml中配置 

<server>  
     <id>tomcat8</id>  
     <username>tomcat</username>  
     <password>tomcat</password>  
</server> 

3.在pom中添加

<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>2.2</version>
				<configuration>
					<url>http://localhost:8080/manager/text</url>
					<server>tomcat8</server>
					<path>/${project.artifactId}</path>
				</configuration>
			</plugin>	
</plugins>

4.run命令

tomcat7:redeploy			
			
			