CREATE TABLE IF NOT EXISTS public.actor
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    achievements character varying(255) COLLATE pg_catalog."default",
    age integer NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default",
    lastname character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT actor_pkey PRIMARY KEY (id)
)