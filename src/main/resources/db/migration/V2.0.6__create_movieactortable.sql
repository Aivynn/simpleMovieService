CREATE TABLE IF NOT EXISTS public.movies
(
    movie_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    actor_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fkiu7d0kk2rjtqp5sw70ave2tfb FOREIGN KEY (movie_id)
        REFERENCES public.movie (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkmrfqaupastn4o1nrx5ixhlya FOREIGN KEY (actor_id)
        REFERENCES public.actor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)