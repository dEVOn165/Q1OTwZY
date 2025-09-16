// 代码生成时间: 2025-09-16 17:12:10
package com.example.theme;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ThemeService {

    private final ThemeMapper themeMapper;

    @Autowired
    public ThemeService(ThemeMapper themeMapper) {
        this.themeMapper = themeMapper;
    }

    @Transactional(readOnly = true)
    public Theme getCurrentTheme() {
        try {
            return themeMapper.getCurrentTheme();
        } catch (Exception e) {
            // Handle any database access exceptions
            throw new RuntimeException("Failed to retrieve current theme", e);
        }
    }

    @Transactional
    public void updateTheme(@Param("userId") int userId, @Param("theme") String theme) {
        try {
            themeMapper.updateTheme(userId, theme);
        } catch (Exception e) {
            // Handle any database access exceptions
            throw new RuntimeException("Failed to update theme", e);
        }
    }
}

/**
 * ThemeMapper.java
 *
 * @author Your Name
 * @version 1.0
 */

package com.example.theme;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ThemeMapper {

    @Select("SELECT theme FROM themes WHERE user_id = #{userId}")
    Theme getCurrentTheme(@Param("userId") int userId);

    @Update("UPDATE themes SET theme = #{theme} WHERE user_id = #{userId}")
    void updateTheme(@Param("userId\) int userId, @Param("theme") String theme);
}

/**
 * Theme.java
 *
 * @author Your Name
 * @version 1.0
 */

package com.example.theme;

public class Theme {
    private String theme;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
