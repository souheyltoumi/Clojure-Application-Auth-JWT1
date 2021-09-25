# Clojure-Application-Auth-JWT1

# Create images Directory in the application folder:
    $ mkdir application/server_images
# Launch the Server to serve users images:
    (requirements: python,pip and SimpleHTTPServer package)
    $ pip install SimpleHTTPServer
    $ cd application/server_images
    $ python -m SimpleHTTPServer 8008
### Directory is accessible on address localhost:8008

# Change DB settings db-spec variable in Register & Login Managers:
    - application/src/application/Managers/loginManager.clj
    - application/src/application/Managers/registerManager.clj
# Create User table in your Database (Name in spec-db variable):
    CREATE TABLE IF NOT EXISTS public.users
    (
        id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
        email character varying(250) NOT NULL,
        password character varying(250) NOT NULL,
        username character varying(250) NOT NULL,
        PRIMARY KEY (id),
        UNIQUE (username),
        UNIQUE (email)
    )

# Install Frontend NodePackages (Angular):
    $ cd application-frontend/
    $ npm install


# Launch the Frontend Application (Angular):
    $ ng serve
### Application should run default on port 4200

# Launch Backend:
    $ cd ../application/
    $ lein run 

### Application should run on port 3000 and you should be good to go
