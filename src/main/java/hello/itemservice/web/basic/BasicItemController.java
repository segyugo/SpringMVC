package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")

public class BasicItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    };

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("A", 1000, 1));
        itemRepository.save(new Item("B", 2000, 2));
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId , Model model){

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String add(){
        return "basic/addForm";
    }


    /*@PostMapping("/add")*/
    public String saveV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model){
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute(item);
        // model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item,
                       Model model) {
        itemRepository.save(item);
        //model.addAttribute(item);
        //model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveV3(@ModelAttribute Item item,
                        Model model) {
        itemRepository.save(item);
        //model.addAttribute(item);
        //model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveV4(@ModelAttribute Item item,
                         Model model) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String saveV5(@ModelAttribute Item item,
                         RedirectAttributes redirectAttributes) {

        Long itemId = itemRepository.save(item).getId();
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(
            @PathVariable("itemId") Long itemId,
            Model model
    ){
        Item item = itemRepository.findById(itemId);
        model.addAttribute(item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(
            @PathVariable("itemId") Long itemId,
            @ModelAttribute Item item,
            Model model
    ){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}




