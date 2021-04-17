FROM timbru31/java-node:11-jdk-erbium

RUN apt update
RUN apt install -y maven
RUN npm install -g npm

# TODO: "COPY" all files from the current directory over to Docker.
COPY . /app
WORKDIR /app

RUN cd backend && mvn clean package
RUN cd frontend && npm install && npm run build
WORKDIR "/app/backend"
CMD [ "./run", "-p=8080"]