CREATE TABLE users (
  id              BIGSERIAL   PRIMARY KEY,
  username        VARCHAR(50) NOT NULL UNIQUE,
  email           VARCHAR(100)NOT NULL UNIQUE,
  password_hash   VARCHAR(255)NOT NULL,
  full_name       VARCHAR(150),
  created_at      TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at      TIMESTAMP   NOT NULL DEFAULT now()
);
CREATE TABLE room_types (
  id                SERIAL    PRIMARY KEY,
  name              VARCHAR(50) NOT NULL UNIQUE,        -- e.g. Single, Double, Suite
  description       TEXT,
  capacity          INT        NOT NULL,                -- nº de huéspedes
  price_per_night   NUMERIC(10,2) NOT NULL
);
CREATE TABLE rooms (
  id             SERIAL     PRIMARY KEY,
  room_number    VARCHAR(10) NOT NULL UNIQUE,           -- e.g. "101", "201A"
  room_type_id   INT        NOT NULL  REFERENCES room_types(id),
  floor          INT,
  status         VARCHAR(20) NOT NULL  DEFAULT 'free',  -- free, occupied, maintenance
  created_at     TIMESTAMP  NOT NULL DEFAULT now()
);
CREATE TABLE reservations (
  id              BIGSERIAL   PRIMARY KEY,
  user_id         BIGINT      NOT NULL REFERENCES users(id),
  room_id         INT         NOT NULL REFERENCES rooms(id),
  check_in_date   DATE        NOT NULL,
  check_out_date  DATE        NOT NULL,
  status          VARCHAR(20) NOT NULL DEFAULT 'pending',  -- pending, confirmed, cancelled
  total_price     NUMERIC(10,2) NOT NULL,
  created_at      TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at      TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT chk_dates CHECK (check_out_date > check_in_date)
);
CREATE TABLE payments (
  id                    BIGSERIAL   PRIMARY KEY,
  reservation_id        BIGINT      NOT NULL REFERENCES reservations(id),
  amount                NUMERIC(10,2) NOT NULL,
  payment_method        VARCHAR(30) NOT NULL,           -- e.g. credit_card, paypal
  payment_date          TIMESTAMP   NOT NULL DEFAULT now(),
  status                VARCHAR(20) NOT NULL DEFAULT 'pending', -- pending, completed, failed
  transaction_reference VARCHAR(100),
  created_at            TIMESTAMP   NOT NULL DEFAULT now()
);
