CREATE TABLE IF NOT EXISTS streams (
    stream_number INT PRIMARY KEY CONSTRAINT positive_stream CHECK (stream_number > 0)
);

CREATE TABLE IF NOT EXISTS study_groups (
    group_number INT PRIMARY KEY CONSTRAINT positive_group CHECK (group_number > 0),
    stream INT REFERENCES streams (stream_number) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id SERIAL PRIMARY KEY,
    full_name VARCHAR(60) NOT NULL,
    study_year INT CONSTRAINT correct_student_study_year CHECK (((study_year > 0) AND (study_year < 7)) OR (study_year IS NULL)),
    group_number INT REFERENCES study_groups (group_number) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS teachers (
    teacher_id SERIAL PRIMARY KEY,
    full_name VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(40) NOT NULL, 
    coverage VARCHAR(9) NOT NULL,
    intensity INT CONSTRAINT correct_intensity CHECK ((intensity > 0) AND (intensity < 8)) NOT NULL,
    study_year INT CONSTRAINT correct_course_study_year CHECK (((study_year > 0) AND (study_year < 7)) OR (study_year IS NULL)),
    teacher INT REFERENCES teachers (teacher_id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS listeners (
    listener_id SERIAL PRIMARY KEY,
    listener_type VARCHAR(7) NOT NULL,
    listener_num INT NOT NULL,
    course INT REFERENCES courses(course_id) ON DELETE CASCADE NOT NULL,
    UNIQUE (listener_type, listener_num, course)
);

CREATE TABLE IF NOT EXISTS passed_courses (
    passed_course_id SERIAL PRIMARY KEY,
    student INT REFERENCES students (student_id) ON DELETE CASCADE NOT NULL,
    course INT REFERENCES courses (course_id) ON DELETE CASCADE NOT NULL,
    study_year INT CONSTRAINT correct_passed_courses_study_year CHECK ((study_year > 0) AND (study_year < 7)) NOT NULL,
    UNIQUE (student, course)
);

CREATE TABLE IF NOT EXISTS classrooms (
    classroom_number INT PRIMARY KEY,
    capacity INT CONSTRAINT positive_capacity CHECK (capacity > 0) NOT NULL
);

CREATE TABLE IF NOT EXISTS lessons (
    lesson_id SERIAL PRIMARY KEY,
    course INT REFERENCES courses (course_id) ON DELETE CASCADE NOT NULL,
    classroom INT REFERENCES classrooms (classroom_number) ON DELETE CASCADE NOT NULL,
    week_day INT CONSTRAINT correct_week_day CHECK ((week_day > 0) AND (week_day < 8)) NOT NULL,
    lesson_time TIME NOT NULL,
    UNIQUE (classroom, week_day, lesson_time),
    UNIQUE (course, week_day, lesson_time)
);