CREATE TABLE IF NOT EXISTS public.producer
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    achievements character varying(255) COLLATE pg_catalog."default",
    age integer NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default",
    lastname character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT producer_pkey PRIMARY KEY (id)
)