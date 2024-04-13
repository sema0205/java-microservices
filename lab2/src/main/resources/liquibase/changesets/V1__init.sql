CREATE TABLE IF NOT EXISTS owner
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    birthdate  TIMESTAMPTZ           NOT NULL,
    created_at TIMESTAMPTZ           NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS cat
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    birthdate  TIMESTAMPTZ           NOT NULL,
    breed      VARCHAR(50)           NOT NULL,
    color      VARCHAR(50)           NOT NULL,
    created_at TIMESTAMPTZ           NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS cat_owner_item
(
    owner_id   BIGINT      NOT NULL REFERENCES owner (id) ON DELETE CASCADE ON UPDATE CASCADE,
    cat_id     BIGINT      NOT NULL REFERENCES cat (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE (owner_id, cat_id)
);

CREATE TABLE IF NOT EXISTS cat_friend_item
(
    cat_id        BIGINT      NOT NULL REFERENCES cat (id) ON UPDATE CASCADE ON DELETE CASCADE,
    friend_cat_id BIGINT      NOT NULL REFERENCES cat (id) ON UPDATE CASCADE ON DELETE CASCADE,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE (cat_id, friend_cat_id)
);
