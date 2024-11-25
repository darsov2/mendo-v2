package mk.ukim.finki.mendo.model.enums;

public enum Grade {
    PRIMARY_1("Прво одделение"),
    PRIMARY_2("Второ одделение"),
    PRIMARY_3("Трето одделение"),
    PRIMARY_4("Четврто одделение"),
    PRIMARY_5("Петто одделение"),
    PRIMARY_6("Шесто одделение"),
    PRIMARY_7("Седмо одделение"),
    PRIMARY_8("Осмо одделение"),
    PRIMARY_9("Деветто одделение"),
    SECONDARY_1("Прва година"),
    SECONDARY_2("Втора година"),
    SECONDARY_3("Трета година"),
    SECONDARY_4("Четврта година"),
    GRADUATE("Завршено средно образование");

    private final String name;
    public String getPrettyName() {
        return name;
    }

    Grade(String name) {
        this.name = name;
    }
}
