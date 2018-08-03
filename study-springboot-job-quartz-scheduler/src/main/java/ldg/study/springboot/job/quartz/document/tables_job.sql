DROP TABLE IF EXISTS gaea_quartz.QUARTZ_JOB;

CREATE TABLE gaea_quartz.QUARTZ_JOB
  (
    job_name VARCHAR(200) NOT NULL,
    job_group VARCHAR(200) NOT NULL,
    concurrent VARCHAR(1) NOT NULL,
    cron_expression VARCHAR(200) NOT NULL,
    rest_url VARCHAR(256) NOT NULL,
    status VARCHAR(3) NOT NULL,
    description VARCHAR(250) NULL,
    PRIMARY KEY (job_name,job_group)
);

commit;
