FROM mysql:5.7

ADD bla.sql bla.sql
ADD setup-database.sh setup-database.sh
RUN chmod +x /setup-database.sh

ENTRYPOINT ["/setup-database.sh"]

CMD ["mysqld"]