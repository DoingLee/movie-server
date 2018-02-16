package com.homework.common.bean;

import com.homework.common.text.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：ldy on 12/02/2018 15:10
 */
public class BeanConvertUtils {
    private static final Logger LOG = LoggerFactory.getLogger(BeanConvertUtils.class);

    public static <FROM, TO> TO deepSafeConvert(FROM dto, Class<FROM> fromClass, Class<TO> toClass) {
        try {
            if (dto == null) {
                return null;
            }
            String json = GsonUtils.toJsonWithDisableEscape(dto);
            return GsonUtils.fromJson(json, toClass);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static <FROM, TO> List<TO> deepSafeConvert(List<FROM> dtos, Class<FROM> fromClass, Class<TO> toClass) {
        try {
            if (CollectionUtils.isEmpty(dtos)) {
                return new ArrayList<TO>(0);
            }
            List<TO> newDtos = new ArrayList<TO>();
            for (FROM dto : dtos) {
                newDtos.add(BeanConvertUtils.deepSafeConvert(dto, fromClass, toClass));
            }
            return newDtos;
        } catch (Exception e) {
            throw new RuntimeException("can not convert",e);
        }
    }

    public static <FROM, TO> TO safeConvert(FROM dto, Class<FROM> fromClass, Class<TO> toClass) {
        return BeanConvertUtils.convert(dto, fromClass, toClass);
    }

    public static <FROM, TO> List<TO> safeConvert(List<FROM> dtos, Class<FROM> fromClass, Class<TO> toClass) {
        return BeanConvertUtils.convert(dtos, fromClass, toClass);
    }

    public static <FROM, TO> TO convert(FROM dto, Class<FROM> fromClass, Class<TO> toClass) {
        try {

            if (dto == null) {
                return null;
            }
            TO newDto = null;
            newDto = toClass.newInstance();

            BeanCopier copier = BeanCopier.create(fromClass, toClass, false);
            copier.copy(dto, newDto, null);
            return newDto;

        } catch (Exception e) {
            throw new RuntimeException("can not convert",e);
        }
    }

    public static <FROM, TO> List<TO> convert(List<FROM> dtos, Class<FROM> fromClass, Class<TO> toClass) {
        try {
            if (CollectionUtils.isEmpty(dtos)) {
                return new ArrayList<TO>(0);
            }
            List<TO> newDtos = new ArrayList<TO>();
            for (FROM dto : dtos) {
                newDtos.add(BeanConvertUtils.convert(dto, fromClass, toClass));
            }
            return newDtos;
        } catch (Exception e) {
            throw new RuntimeException("can not convert",e);
        }
    }

    public static <FROM, TO> void copy(FROM dto, TO toDto, Class<FROM> fromClass, Class<TO> toClass) {
        try {
            if (dto == null) {
                return;
            }
            if (toDto == null) {
                toDto = toClass.newInstance();
            }
            BeanCopier copier = BeanCopier.create(fromClass, toClass, false);
            copier.copy(dto, toDto, null);

            return;
        } catch (Exception e) {
            throw new RuntimeException("can not convert",e);
        }
    }
}
