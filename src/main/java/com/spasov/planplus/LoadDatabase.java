package com.spasov.planplus;

import com.spasov.planplus.config.SecurityConfig;
import com.spasov.planplus.entity.*;
import com.spasov.planplus.service.OrderService;
import com.spasov.planplus.service.ProductService;
import com.spasov.planplus.service.RoleService;
import com.spasov.planplus.service.UserService;
import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@Transactional
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, ProductService productService, OrderService orderService) {
        return args -> {
            // Создание ролей
            Role roleAdmin = Role.builder().name(RoleEnum.ADMIN).build();
            Role roleUser = Role.builder().name(RoleEnum.USER).build();

            roleService.save(roleAdmin);
            roleService.save(roleUser);

            // Создание пользователей
            User userAdmin = User.builder()
                    .name("Админ Иван")
                    .email("admin@mail.com")
                    .password("Password1!")
                    .roles(Set.of(roleAdmin))
                    .build();

            User userUser = User.builder()
                    .name("Юзер Иван")
                    .email("user@mail.com")
                    .password("Password1!")
                    .roles(Set.of(roleUser))
                    .build();


            User userAUM = User.builder()
                    .name("Уникальный Иван")
                    .email("unique@mail.com")
                    .password("Password1!")
                    .roles(Set.of(roleAdmin, roleUser))
                    .build();

            userService.save(userAdmin);
            userService.save(userUser);
            userService.save(userAUM);

            Product cardConsultation = Product.builder()
                    .name("Телефонная консультация по услугам и другим вопросам")
                    .price(0)
                    .description("Телефонная консультация по услугам и другим вопросам")
                    .imageUrl("/image/card_business_plan.jpeg").build();
            productService.save(cardConsultation);

            Product cardCars = Product.builder()
                    .name("Бизнес-план для соцконтракта: Курьерская доставка на автомобиле")
                    .price(6000)
                    .description("Ищете бизнес-план в сфере курьерской доставки? " +
                            "Мы предлагаем готовый бизнес-план для курьерской доставки" +
                            " на автомобиле. Наша компания \"План+\" - лидер на рынке " +
                            "консалтинговых услуг и первый интернет-магазин готовых бизнес-планов " +
                            "для соцконтрактов. Наш бизнес-план включает готовые расчеты, описание " +
                            "услуг такси и курьерской доставки грузов на авто. Вы сэкономите более " +
                            "75% бюджета, а вероятность одобрения социального контракта повысится " +
                            "до 100%. Документ будет адаптирован под ваш регион и позволит сократить " +
                            "время на подготовку документов с 10 дней до 3 часов. Приобретайте " +
                            "бизнес-план курьерской доставки на автомобиле и начните свой бизнес, " +
                            "используя нашу уникальную поддержку и подробные инструкции!")
                    .imageUrl("/image/card_business_plan.jpeg").build();
            productService.save(cardCars);

            Product cardPhoto = Product.builder()
                    .name("Бизнес-план для соцконтракта: услуги фотографа и видеографа")
                    .price(6000)
                    .description("Планируете открыть бизнеса в сфере фото- и видеосъемки за счет соцконтракта? Наш готовый бизнес-план услуг фотографа и видеографа – ваш путь к успеху! Мы, ведущий интернет-магазин готовых бизнес-планов для соцконтрактов, предлагаем уникальные решения с готовыми расчетами и подробным описанием.")
                    .imageUrl("/image/card_business_plan.jpeg").build();
            productService.save(cardPhoto);

            Product cardOzon = Product.builder()
                    .name("Бизнес-план для соцконтракта: ПВЗ OZON, форма ИП")
                    .price(6000)
                    .description("Ищете проверенную стратегию для старта в сфере пункта выдачи заказов OZON с доходностью до 1 378 140 ₽? Добро пожаловать в \"Мой План\"! Наша компания является лидером в консалтинге и вашим идеальным проводником к получению социального контракта суммой до 350 000 ₽.")
                    .imageUrl("/image/card_business_plan.jpeg").build();
            productService.save(cardOzon);

            Product cardNails = Product.builder()
                    .name("Бизнес-план для соцконтракта: Маникюр и педикюр.")
                    .price(6000)
                    .description("Ищете бизнес-план для старта успешного салона маникюра и педикюра? Мы предлагаем готовый бизнес-план, специально разработанный для этого сегмента. Наша компания \"План+\" — лидер на рынке консалтинговых услуг и первый интернет-магазин готовых бизнес-планов для соцконтрактов. Наш бизнес-план включает готовые финансовые расчеты, описание услуг маникюра и педикюра, а также стратегию продвижения вашего салона. С нами вы сможете сэкономить более 75% бюджета, и вероятность одобрения социального контракта повысится до 100%. Документ будет адаптирован под ваш регион и сократит время на подготовку всех необходимых документов с 10 дней до всего 3 часов.")
                    .imageUrl("/image/card_business_plan.jpeg").build();
            productService.save(cardNails);

            Product cardOther = Product.builder()
                    .name("Другой бизнес-план для соцконтракта")
                    .price(4000)
                    .description("Устали от неопределенности и  поиска идей? Мы предлагаем готовый бизнес-план \"Другой бизнес-план для соцконтракта\", созданный специально для тех, кто  ищет нестандартные и эффективные решения для старта своего дела. Не тратьте время на  разработку с нуля!  " +
                            "С нашим бизнес-планом вы получаете:" +
                            "* Готовые финансовые расчеты, адаптированные к вашему региону" +
                            "* Проработанную  маркетинговую стратегию,  которая поможет вам привлечь клиентов и получить прибыль " +
                            "* Детальное описание  бизнес-модели,  позволяющей  максимально  эффективно  использовать  средства соцконтракта" +
                            "Мы гарантируем:" +
                            "Экономию  времени  и  денег " +
                            "Повышение  шансов  на  получение  социального  контракта " +
                            "Профессиональную  поддержку  на  всех  этапах  реализации  проекта" +
                            "Закажите \"Другой бизнес-план для соцконтракта\" уже сегодня и сделайте первый шаг к успешному бизнесу!" +
                            "Мы также предлагаем  услуги по  созданию  индивидуальных бизнес-планов, адаптированных  под  ваши  требования  и  конкретную  идею.")
                    .imageUrl("/image/card_business_plan.jpeg").build();
            productService.save(cardOther);

            Order order = Order.builder()
                    .timestamp(LocalDateTime.now().minusDays(1))
                    .phoneNumber("89999999999")
                    .status(OrderStatus.NOT_PROCESSED)
                    .products(new ArrayList<>(List.of(cardOther, cardNails)))
                    .build();
            orderService.save(order);

            Order order2 = Order.builder()
                    .timestamp(LocalDateTime.now().minusDays(3))
                    .phoneNumber("81111111111")
                    .status(OrderStatus.NOT_PROCESSED)
                    .user(userUser)
                    .products(new ArrayList<>(List.of(cardConsultation)))
                    .build();
            orderService.save(order2);

            Order order3 = Order.builder()
                    .timestamp(LocalDateTime.now().minusDays(10))
                    .phoneNumber("81111122222")
                    .status(OrderStatus.IN_PROGRESS)
                    .user(userUser)
                    .products(new ArrayList<>(List.of(cardOzon, cardCars, cardNails)))
                    .build();
            orderService.save(order3);

        };
    }
}