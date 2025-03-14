package model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter()
public enum Attendance_ENUM implements AttributeConverter<Attendance_ENUM, Integer> {
     ABSENT(0),PRESENT(1);

    private int attendance;

    Attendance_ENUM(int attendance) {
        this.attendance = attendance;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public static Attendance_ENUM attendanceToEnum(int attendance) {
        for (Attendance_ENUM attendanceEnum : Attendance_ENUM.values()) {
            if (attendanceEnum.getAttendance() == attendance) {
                return attendanceEnum;
            }
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseColumn(Attendance_ENUM a) {
        return a.getAttendance();
    }

    @Override
    public Attendance_ENUM convertToEntityAttribute(Integer integer) {
        return attendanceToEnum(integer);
    }






}
