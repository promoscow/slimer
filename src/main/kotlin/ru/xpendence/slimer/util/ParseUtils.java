package ru.xpendence.slimer.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import ru.xpendence.slimer.entity.Product;
import ru.xpendence.slimer.repository.ProductRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 29.06.19
 * Time: 11:09
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
public class ParseUtils {

    private final ProductRepository productRepository;

    public ParseUtils(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Scheduled(initialDelay = 1000, fixedDelay = 1000000)
    public void parseProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(new File("/home/v-chernyshov/calories.xls"));
        Workbook workbook = new HSSFWorkbook(excelFile);
        Sheet sourceSheet = workbook.getSheetAt(0);

        for (Row currentRow : sourceSheet) {
            if (currentRow.getRowNum() > 0) {
                Product product = new Product();
                product.setName(currentRow.getCell(0).getStringCellValue());
                product.setProteins(currentRow.getCell(1).getNumericCellValue());
                product.setFats(currentRow.getCell(2).getNumericCellValue());
                product.setCarbohydrates(currentRow.getCell(3).getNumericCellValue());
                product.setCalories((int) currentRow.getCell(4).getNumericCellValue());
                products.add(product);
            }
        }

        System.out.println("products total: " + products.size());
        productRepository.saveAll(products);
    }
}
