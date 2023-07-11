# TODO: Double check command to run the database
run-db:
    @docker run -p 27017:27017 -v mongodata:/data/db -d mongo:latest
