CREATE TYPE DAY_OF_WEEK AS ENUM (
    'Понедельник', 
    'Вторник', 
    'Среда', 
    'Четверг', 
    'Пятница', 
    'Суббота', 
    'Воскресенье'
);

CREATE TYPE COURSE_TYPE AS ENUM (
    'Потоковый',
    'Групповой',
    'Спецкурс'
);

CREATE TYPE LISTENERS_TYPE AS ENUM (
    'Студент',
    'Группа',
    'Поток'
);

CREATE TABLE IF NOT EXISTS streams (
    stream_number INT PRIMARY KEY CONSTRAINT positive_stream CHECK (stream_number > 0)
);

CREATE TABLE IF NOT EXISTS groups (
    group_number INT PRIMARY KEY CONSTRAINT positive_group CHECK (group_number > 0),
    stream INT REFERENCES streams (stream_number) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id SERIAL PRIMARY KEY,
    full_name VARCHAR(60) NOT NULL,
    study_year INT CONSTRAINT correct_student_study_year CHECK ((study_year > 0) && (study_year < 7)),
    group_number REFERENCES groups (group_number) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(40) NOT NULL, 
    coverage COURSE_TYPE NOT NULL,
    intensity INT CONSTRAINT correct_intensity CHECK ((intensity > 0) && (intensity < 8)) NOT NULL,
    study_year INT CONSTRAINT correct_course_study_year CHECK ((study_year > 0) && (study_year < 7))
);

CREATE TABLE IF NOT EXISTS listeners (
    listener_type LISTENERS_TYPE NOT NULL,
    id INT NOT_NULL,
    course INT REFERENCES courses(course_id) NOT NULL ON DELETE CASCADE
    PRIMARY KEY (listener_type, id, course)
);

CREATE TABLE IF NOT EXISTS teachers (
    teacher_id SERIAL PRIMARY KEY,
    full_name VARCHAR(60) NOT NULL,
    courses INT[]
);

CREATE TABLE IF NOT EXISTS passed_courses (
    student INT REFERENCES students (student_id) NOT NULL ON DELETE CASCADE,
    course INT REFERENCES courses (course_id) NOT NULL ON DELETE CASCADE,
    study_year INT CONSTRAINT correct_passed_courses_study_year CHECK ((study_year > 0) && (study_year < 7))
    PRIMARY KEY (student, course)
);

CREATE TABLE IF NOT EXISTS classrooms (
    classroom_number INT PRIMARY KEY,
    capacity INT CONSTRAINT positive_capacity CHECK (capacity > 0) NOT NULL
);

CREATE TABLE IF NOT EXISTS lessons (
    teacher INT REFERENCES teachers (teacher_id) ON DELETE SET NULL,
    course INT REFERENCES courses (course_id) NOT NULL ON DELETE CASCADE,
    classroom INT REFERENCES classrooms (classroom_number) ON DELETE SET NULL,
    day DAY_OF_WEEK NOT NULL,
    lesson_time TIME NOT NULL
);