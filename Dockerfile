FROM mysql:9.6

HEALTHCHECK --interval=10s --timeout=5s --start-period=30s --retries=5 \
    CMD mysqladmin ping --host=127.0.0.1 --user="$MYSQL_USER" --password="$MYSQL_PASSWORD" --silent || exit 1
