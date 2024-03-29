import java.util.Scanner;

// Интерфейс итератора
interface ChannelIterator {
    String nextChannel();
    String previousChannel();
}

// Реализация итератора для телевизоров с пультами с кнопками
class ButtonRemoteIterator implements ChannelIterator {
    @Override
    public String nextChannel() {
        return "Был найден следующий канал путем выбора следующей частоты из сформированной заранее таблицы частот";
    }

    @Override
    public String previousChannel() {
        return "Был найден предыдущий канал путем выбора предыдущей частоты из сформированной заранее таблицы частот";
    }
}

// Реализация итератора для телевизоров с пультами с потенциометрами
class PotentiometerRemoteIterator implements ChannelIterator {
    @Override
    public String nextChannel() {
        return "Был найден следующий канал путем сканирования частот в режиме реального времени";
    }

    @Override
    public String previousChannel() {
        return "Был найден предыдущий канал путем изменения частоты в обратном направлении в режиме реального времени";
    }
}

// Класс для управления телевизором
class TVController {
    private ChannelIterator iterator;

    public void setIterator(ChannelIterator iterator) {
        this.iterator = iterator;
    }

    public void switchNextChannel() {
        System.out.println(iterator.nextChannel());
    }

    public void switchPreviousChannel() {
        System.out.println(iterator.previousChannel());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TVController controller = new TVController();
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Выбрать тип телевизора");
            System.out.println("2. Выйти");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Выберите тип телевизора:");
                    System.out.println("1. С пультом с кнопками");
                    System.out.println("2. С пультом с потенциометром");
                    int tvChoice = scanner.nextInt();

                    if (tvChoice == 1) {
                        controller.setIterator(new ButtonRemoteIterator());
                    } else if (tvChoice == 2) {
                        controller.setIterator(new PotentiometerRemoteIterator());
                    }

                    while (true) {
                        System.out.println("Выберите команду:");
                        System.out.println("1. Следующий канал");
                        System.out.println("2. Предыдущий");
                        System.out.println("3. Выйти в предыдущее меню");

                        int command = scanner.nextInt();

                        if (command == 1) {
                            controller.switchNextChannel();
                        } else if (command == 2) {
                            controller.switchPreviousChannel();
                        } else if (command == 3) {
                            break;
                        }
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }
}
