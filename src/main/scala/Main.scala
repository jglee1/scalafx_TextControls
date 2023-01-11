import java.awt.TextArea
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField, TextArea, PasswordField}
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, CheckBox, RadioButton, ToggleButton,
  ToggleGroup, Label}
import scalafx.scene.control.{ComboBox, ListView, ChoiceBox, TableView, TableColumn, TreeView, TreeItem}
import scalafx.scene.control.{DatePicker, ColorPicker}
import java.time.LocalDate
import scalafx.scene.paint.Color
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty, ObjectProperty}


object NewApp0 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "First GUI"
      scene = new Scene (400,300) {
        val button = new Button("Click Me!")
        //val button = new Button("Delete!")
        button.layoutX = 100
        button.layoutY = 50

        val comboBox = new ComboBox(List("Scala", "Java", "C++", "Haskell"))
        comboBox.layoutX = 200
        comboBox.layoutY = 50

        val listView = new ListView(List("AWT", "Swing", "JavaFX", "ScalaFX"))
        listView.layoutX = 100
        listView.layoutY = 100
        listView.prefHeight = 150

        content = List(button, comboBox, listView)

        button.onAction = (e:ActionEvent) => {
          val selected = listView.selectionModel.apply().getSelectedItems
          listView.items = listView.items.apply().diff(selected)
          //println("Button clicked.")
        }

        comboBox.onAction = (e:ActionEvent) => {
          listView.items.apply() += comboBox.selectionModel.apply().getSelectedItem
        }
      }
    }
  }
}


object NewApp1 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Text Controls"
      scene = new Scene(400, 400) {
        val label = new Label("This is a label.")
        label.layoutX = 20
        label.layoutY = 20

        val textField = new TextField
        textField.layoutX = 20
        textField.layoutY = 50
        textField.promptText = "A field"

        val textArea = new scalafx.scene.control.TextArea
        textArea.layoutX = 20
        textArea.layoutY = 80
        textArea.prefHeight = 200
        textArea.prefWidth = 360
        textArea.promptText = "Area"

        val passwordField = new PasswordField
        passwordField.layoutX = 20
        passwordField.layoutY = 300
        passwordField.promptText = "Password"

        content = List(label, textField, textArea, passwordField)

        textField.onAction = (e: ActionEvent) => {
          label.text = "Field action: " + textField.text.apply()
        }

        textArea.text.onChange {
          label.text = "Area changed: " + textArea.text.apply()
        }

        passwordField.focused.onChange {
          label.text = "Password focus changed. " + passwordField.focused.apply()
        }
      }
    }
  }
}


object NewApp2 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Text Controls"
      scene = new Scene(400, 600) {
        val label = new Label("Feedback")
        label.layoutX = 20
        label.layoutY = 20

        val button = new Button("A Button")
        button.layoutX = 20
        button.layoutY = 50

        val cb1 = new CheckBox("Check 1")
        cb1.layoutX = 20
        cb1.layoutY = 80
        val cb2 = new CheckBox("Check 2")
        cb2.layoutX = 20
        cb2.layoutY = 110

        val rb1 = new RadioButton("Radio 1")
        rb1.layoutX = 20
        rb1.layoutY = 140
        val rb2 = new RadioButton("Radio 2")
        rb2.layoutX = 20
        rb2.layoutY = 170
        val rb3 = new RadioButton("Radio 3")
        rb3.layoutX = 20
        rb3.layoutY = 200
        val toggle1 = new ToggleGroup
        toggle1.toggles = List(rb1, rb2, rb3)

        val tb1 = new ToggleButton("Toggle 1")
        tb1.layoutX = 20
        tb1.layoutY = 230
        val tb2 = new ToggleButton("Toggle 2")
        tb2.layoutX = 20
        tb2.layoutY = 260
        val tb3 = new ToggleButton("Toggle 3")
        tb3.layoutX = 20
        tb3.layoutY = 290
        val toggle2 = new ToggleGroup
        toggle2.toggles = List(tb1, tb2, tb3)

        content = List(label, button, cb1, cb2, rb1, rb2, rb3, tb1, tb2, tb3)

        button.onAction = (e: ActionEvent) => {
          label.text = "Button clicked"
        }
        cb1.onAction = (e: ActionEvent) => {
          label.text = "cb1 clicked " +cb1.selected.apply()
        }
        rb1.onAction = (e: ActionEvent) => {
          label.text = "rb1 clicked " + rb1.selected.apply()
        }
        rb1.selected.onChange {
          label.text = "rb1 selection changed " + rb1.selected.apply()
        }
        tb1.onAction = (e: ActionEvent) => {
          label.text = "tb1 clicked " + tb1.selected.apply()
        }
      }
    }
  }
}

object NewApp3 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Selection Controls"
      scene = new Scene(600, 300) {
        val label = new Label("Feedback")
        label.layoutX = 20
        label.layoutY = 20

        val comboBox = new ComboBox(List("Combo 1", "Combo 2", "Combo 3"))
        comboBox.layoutX = 20
        comboBox.layoutY = 50

        val listView = new ListView(List.tabulate(20)(i => "Option " + (i+1)))
        listView.layoutX = 300
        listView.layoutY = 80
        listView.prefHeight = 260

        content = List(label, comboBox, listView)

        comboBox.onAction = (e: ActionEvent) => {
          label.text = "Combo Selection = " + comboBox.value.apply()
        }

        listView.selectionModel.apply().selectedItems.onChange {
          label.text = "List View = " + listView.selectionModel.apply().getSelectedItems
        }

      }
    }
  }
}


object NewApp5 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Pickers"
      scene = new Scene(300, 300) {
        val label = new Label("Show date")
        label.layoutX = 20
        label.layoutY = 20

        val date = new DatePicker(LocalDate.now)
        date.layoutX = 20
        date.layoutY = 50
        val color = new ColorPicker(Color.White)
        color.layoutX = 20
        color.layoutY = 80

        content = List(label, date, color)

        date.onAction = (e: ActionEvent) => {
          label.text = "Date: " + date.value.apply()
        }

        color.onAction = (e: ActionEvent) => {
          fill = color.value.apply()
        }

      }
    }
  }
}

case class Student(name: String, test1: Int, test2: Int)

object NewApp6 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Table View"
      scene = new Scene(600, 300) {
        val data = ObservableBuffer(
          Student("Jane Doe", 99, 93),
          Student("John Doe", 73, 88),
          Student("Bob Builder", 85, 91)
        )

        val table = new TableView(data)
        val col1 = new TableColumn[Student, String]("Name")
        col1.cellValueFactory = cdf => StringProperty(cdf.value.name)
        val col2 = new TableColumn[Student, Int]("Test 1")
        col2.cellValueFactory = cdf => ObjectProperty(cdf.value.test1)
        val col3 = new TableColumn[Student, Int]("Test 2")
        col3.cellValueFactory = cdf => ObjectProperty(cdf.value.test2)
        val col4 = new TableColumn[Student, Double]("Average")
        col4.cellValueFactory = cdf => ObjectProperty( (cdf.value.test1 + cdf.value.test2)/2.0)

        table.columns ++= List(col1, col2, col3, col4)

        root = table

        table.selectionModel.apply().selectedItem.onChange {
          println("Selected " + table.selectionModel.apply().getSelectedItems)
        }

      }
    }
  }
}


object NewApp7 extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Tree View"
      scene = new Scene(600, 300) {
        val texas = new TreeItem("Texas")
        texas.children = List(new TreeItem("Houston"), new TreeItem("Dallas"), new TreeItem("San Antonio"))
        val cali = new TreeItem("California")
        cali.children = List(new TreeItem("LA"), new TreeItem("San Francisco"), new TreeItem("Sacramento"))
        val us = new TreeItem("US")
        us.children = List(texas, cali)

        val tree = new TreeView(us)

        root = tree

        tree.selectionModel.apply().selectedItem.onChange {
          println("Selected " + tree.selectionModel.apply().getSelectedItems)
        }
      }
    }
  }
}
