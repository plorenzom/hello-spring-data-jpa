-- Tabla 'team'.
CREATE TABLE team (
    team_id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabla 'player'.
CREATE TABLE player (
    player_id UUID PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    paternal_surname VARCHAR(25) NOT NULL,
    maternal_surname VARCHAR(25),
    nickname VARCHAR(25),
    birth_date DATE NOT NULL,
    sex CHAR(1) NOT NULL,
    CONSTRAINT chk_player_sex CHECK (sex IN ('H', 'M'))
);
