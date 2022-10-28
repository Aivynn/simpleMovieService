CREATE TABLE IF NOT EXISTS public.movie
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    budget integer NOT NULL,
    fees integer NOT NULL,
    genre character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    poster_url character varying(255) COLLATE pg_catalog."default",
    status boolean NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    producer_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT movie_pkey PRIMARY KEY (id),
    CONSTRAINT fkmxrven9dl3e4w2gry20je8qpf FOREIGN KEY (producer_id)
        REFERENCES public.producer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)