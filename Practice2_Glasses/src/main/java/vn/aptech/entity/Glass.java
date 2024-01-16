package vn.aptech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Glass {
	private int glassId;
	private String name;
	private int price;
	private String image;
}
