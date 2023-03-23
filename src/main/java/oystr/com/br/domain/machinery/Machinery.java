package oystr.com.br.domain.machinery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import oystr.com.br.infrastructure.mapper.Mapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Machinery {
  private String model;
  private String contract;
  private String contractType;
  private String make;
  private String year;
  private String workedHours;
  private String city;
  private String price;
  private String picture;

  @Override
  public String toString() {
    return Mapper.toJson(this);
  }
}
