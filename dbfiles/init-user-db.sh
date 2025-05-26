 #!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE customer;
    GRANT ALL PRIVILEGES ON DATABASE customer TO "$POSTGRES_USER";
EOSQL


