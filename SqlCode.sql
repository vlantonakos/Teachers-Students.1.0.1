CREATE TABLE `courses` (
  `Name` varchar(50) NOT NULL,
  `Info` varchar(45) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `students` (
  `ID` varchar(45) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Last Name` varchar(45) NOT NULL,
  `Grade` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;